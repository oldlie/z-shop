import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../../service/register.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  form: FormGroup;
  cellphone = '';
  welcomeInfo = '';

  constructor(private fb: FormBuilder,
    private msg: NzMessageService,
    private r: Router,
    private rs: RegisterService) { }

  ngOnInit() {
    this.cellphone = this.rs.cellphone;
    this.form = this.fb.group({
      nickname: [null, [Validators.required]],
      password: [null, [this.passwordValidator]],
      password2: [null, [this.passwordValidator, this.confirmValidator]],
      image: ['/user.jpg'],
      cellphone: [this.cellphone],
      cellphone2: [null],
      resume: [null]
    });

    if (!this.rs.isVerified) {
      this.r.navigate(['/login']).catch(e => console.error(e));
      return;
    }

    this.welcomeInfo = '欢迎用户：' + this.cellphone;
  }

  validateConfirmPassword() {
    setTimeout(() => this.form.controls.password2.updateValueAndValidity());
  }

  passwordValidator = (control: FormControl): { [s: string]: boolean } => {
    const reg = /^[a-zA-Z0-9_-]{8,32}$/;
    return reg.test(control.value) ? null : { password: true, error: true };
  }

  confirmValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.form.controls.password.value) {
      return { password: true, error: true };
    }
  }

  submitForm() {

    for (const key in this.form.controls) {
      if (this.form.controls.hasOwnProperty(key)) {
        this.form.controls[key].markAsDirty();
        this.form.controls[key].updateValueAndValidity();
      }
    }

    if (!this.form.valid) {
      return;
    }

    const username = this.cellphone;
    const nickname = this.form.value.nickname;
    const password = this.form.value.password;
    const cellphone = this.form.value.cellphone;
    const cellphone2 = this.form.value.cellphone2;
    const resume = this.form.value.resume;
    const image = this.form.value.image;

    this.rs.signup(username, password, nickname, cellphone, cellphone2, resume, image).then(x => {
      if (x.status === 0) {
        this.r.navigate(['/login']).then(e => console.log(e));
      } else {
        this.msg.error(x.message);
      }
    });
  }
}
