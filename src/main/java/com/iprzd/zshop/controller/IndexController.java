package com.iprzd.zshop.controller;

import com.iprzd.zshop.entity.UploadFile;
import com.iprzd.zshop.http.StatusCode;
import com.iprzd.zshop.http.request.FileRequest;
import com.iprzd.zshop.http.response.BaseResponse;
import com.iprzd.zshop.entity.Authority;
import com.iprzd.zshop.entity.User;
import com.iprzd.zshop.http.response.FileResponse;
import com.iprzd.zshop.http.response.ImageResponse;
import com.iprzd.zshop.repository.AuthorityRepository;
import com.iprzd.zshop.repository.UploadFileRepository;
import com.iprzd.zshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class IndexController {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UploadFileRepository uploadFileRepository;

    @Value("${zs.upload}")
    private String uploadDirectory;

    @Value("${zs.version}")
    private String appVersion;

    @Value("${zs.uploadUrl}")
    private String uploadURL;

    public IndexController(AuthorityRepository authorityRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
            UploadFileRepository uploadFileRepository, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.uploadFileRepository = uploadFileRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/")
    public String index() {
        return "当前版本：v" + appVersion;
    }

    @GetMapping("/init")
    public BaseResponse init() {
        BaseResponse response = new BaseResponse();

        Authority authority = this.authorityRepository.findOneByRole("ADMIN");
        if (authority == null) {
            authority = new Authority();
            authority.setRole("ADMIN");
            this.authorityRepository.save(authority);
        }

        Authority userAuthority = this.authorityRepository.findOneByRole("USER");
        if (userAuthority == null) {
            userAuthority = new Authority();
            userAuthority.setRole("USER");
            this.authorityRepository.save(userAuthority);
        }

        Authority proAuthority = this.authorityRepository.findOneByRole("PRO");
        if (proAuthority == null) {
            proAuthority = new Authority();
            proAuthority.setRole("PRO");
            this.authorityRepository.save(proAuthority);
        }

        User admin = this.userRepository.findByUsername("admin@zshop.com");
        if (admin == null) {
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authority);
            admin = new User();
            admin.setUsername("admin@zshop.com");
            admin.setPassword(this.bCryptPasswordEncoder.encode("123456"));
            admin.setAuthorities(authorities);
            this.userRepository.save(admin);
        }

        response.setStatus(0);
        response.setMessage(appVersion);
        return response;
    }

    @PostMapping("/signup")
    public void signUp(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    class Token {
        public String token;
    }

    @PostMapping("/upload")
    public FileResponse uploadFile(MultipartHttpServletRequest request, HttpServletResponse servletResponse) {
        FileResponse response = new FileResponse();

        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;

        Calendar calendar = Calendar.getInstance();
        List<UploadFile> uploadFileList = new ArrayList<>();

        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            StringBuilder fileNameBuilder = new StringBuilder(64);
            fileNameBuilder.append(UUID.randomUUID().toString()).append("_").append(mpf.getOriginalFilename());

            StringBuilder path = new StringBuilder(128);
            path.append(calendar.get(Calendar.YEAR)).append(File.separator) // 上传年
                    .append(calendar.get(Calendar.MONTH)).append(File.separator) // 上传月
                    .append(fileNameBuilder.toString());

            File saveFile = new File(this.uploadDirectory + File.separator + path.toString());
            if (!saveFile.getParentFile().exists()) {
                if (!saveFile.getParentFile().mkdirs()) {
                    response.setStatus(StatusCode.UPLOAD_FILE_FAILED);
                    response.setMessage("服务器没有成功的创建文件夹，请稍后再试");
                }
            }

            try {
                mpf.transferTo(saveFile);

                UploadFile uploadFile = new UploadFile();
                uploadFile.setPath(path.toString());
                uploadFile.setName(fileNameBuilder.toString());
                uploadFile.setCreateAt(calendar.getTime());

                uploadFile = this.uploadFileRepository.save(uploadFile);

                uploadFileList.add(uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(StatusCode.UPLOAD_FILE_FAILED);
                response.setMessage(e.getLocalizedMessage());
            }
        }

        response.setList(uploadFileList);
        return response;
    }

    @PostMapping("/image")
    public ImageResponse uploadImage(@RequestBody MultipartFile file) {
        ImageResponse response = new ImageResponse();
        if (file.isEmpty()) {
            response.setStatus(StatusCode.UPLOAD_FILE_FAILED);
            response.setMessage("选择需要上传的文件");
            return response;
        }
        Calendar calendar = Calendar.getInstance();
        String fileName = file.getOriginalFilename();
        int length = fileName.length();
        if (fileName.length() > 15) {
            fileName = fileName.substring(length - 10, length);
        }
        StringBuilder fileNameBuilder = new StringBuilder(64);
        fileNameBuilder.append(calendar.getTime().getTime()).append("_").append(fileName);

        StringBuilder path = new StringBuilder(128);
        path.append(calendar.get(Calendar.YEAR)).append(File.separator) // 上传年
                .append(calendar.get(Calendar.MONTH)).append(File.separator) // 上传月
                .append(fileNameBuilder.toString());

        File savedFile = new File(this.uploadDirectory + File.separator + path.toString());
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();
        }

        UploadFile uploadFile = new UploadFile();
        try {
            file.transferTo(savedFile);

            uploadFile.setPath(path.toString());
            uploadFile.setName(fileNameBuilder.toString());
            uploadFile.setCreateAt(calendar.getTime());

            uploadFile = this.uploadFileRepository.save(uploadFile);
            response.setLink(this.uploadURL + "/" + uploadFile.getPath().replace("\\", "/"));
        } catch (IOException e) {
            response.setStatus(StatusCode.UPLOAD_FILE_FAILED);
            response.setMessage(e.getLocalizedMessage());
        }

        return response;
    }

    @PostMapping("/custom-upload")
    public FileResponse uploadFile(@RequestBody FileRequest request) {
        FileResponse response = new FileResponse();
        if (request.getFile().isEmpty()) {
            response.setStatus(StatusCode.UPLOAD_FILE_FAILED);
            response.setMessage("选择需要上传的文件");
            return response;
        }

        Calendar calendar = Calendar.getInstance();

        String fileName = request.getFile().getOriginalFilename();
        if (fileName.length() > 15) {
            fileName = fileName.substring(0, 15);
        }
        StringBuilder fileNameBuilder = new StringBuilder(64);
        fileNameBuilder.append(calendar.getTime().getTime()).append("_").append(fileName);

        StringBuilder path = new StringBuilder(128);
        path.append(request.getCategory()).append(File.separator) // 上传文件类型
                .append(request.getCreator()).append(File.separator) // 上传账号
                .append(calendar.get(Calendar.YEAR)).append(File.separator) // 上传年
                .append(calendar.get(Calendar.MONTH)).append(File.separator) // 上传月
                .append(fileNameBuilder.toString());

        File file = new File(this.uploadDirectory + File.separator + path.toString());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            request.getFile().transferTo(file);

            UploadFile uploadFile = new UploadFile();
            uploadFile.setPath(path.toString());
            uploadFile.setName(fileNameBuilder.toString());
            uploadFile.setCreatorId(request.getCreatorId());
            uploadFile.setCreator(request.getCreator());
            uploadFile.setCreateAt(calendar.getTime());

            uploadFile = this.uploadFileRepository.save(uploadFile);

        } catch (IOException e) {
            response.setStatus(StatusCode.UPLOAD_FILE_FAILED);
            response.setMessage(e.getLocalizedMessage());
        }
        return response;
    }
}
