import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CoreService } from '../../service/core.service';
import { Router } from '@angular/router';
import { FrontService } from '../../service/front.service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [
    FrontService
  ]
})
export class RegisterComponent implements OnInit {

  form: FormGroup;

  constructor(private cs: CoreService,
    private fb: FormBuilder,
    private fs: FrontService,
    private msg: NzMessageService,
    private r: Router
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      cellphone: [null, [Validators.required]],
      code: [null, [Validators.required]]
    });
  }

  submitForm(): void {
    for (const key in this.form.controls) {
      if (this.form.controls.hasOwnProperty(key)) {
        this.form.controls[key].markAsDirty();
        this.form.controls[key].updateValueAndValidity();
      }
    }

    if (!this.form.valid) {
      return;
    }

    this.fs.register(this.form.value.cellphone, this.form.value.code).then(x => {
      if (x.status === 0) {
        this.msg.success('注册成功，明天改成跳转到完善个人信息页');
      } else {
        this.msg.error(x.message);
      }
    });
  }

  sendCode(e: Event): void {

    this.form.controls['cellphone'].markAsDirty();
    this.form.controls['cellphone'].updateValueAndValidity();

    if (this.form.controls['cellphone'].invalid) {
      return;
    }

    this.fs.sendCode(this.form.value.cellphone).then(x => {
      if (x.status === 0) {
        this.msg.success('验证码已经发送，请查收');
      } else {
        this.msg.error(x.message);
      }
    });
  }
}
