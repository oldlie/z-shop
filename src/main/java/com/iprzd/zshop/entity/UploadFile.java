package com.iprzd.zshop.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_upload_file")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UploadFile implements Serializable {
    private final static long serialVersionUID = 1541846733789L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path;
    private String name;
    private long creatorId;
    private String creator;
    private Date createAt;
}
