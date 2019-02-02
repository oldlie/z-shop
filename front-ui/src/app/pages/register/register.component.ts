import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CoreService } from '../../service/core.service';
import { Router } from '@angular/router';
import { FrontService } from '../../service/front.service';
import { NzMessageService } from 'ng-zorro-antd';
import { RegisterService } from '../../service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  isVerified = false;

  constructor(private cs: CoreService,
    private fb: FormBuilder,
    private msg: NzMessageService,
    private r: Router,
    private rs: RegisterService
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      cellphone: [null, [Validators.required]],
      code: [null, [Validators.required]]
    });
  }

  submitForm(e: Event): void {
    e.preventDefault();

    for (const key in this.form.controls) {
      if (this.form.controls.hasOwnProperty(key)) {
        this.form.controls[key].markAsDirty();
        this.form.controls[key].updateValueAndValidity();
      }
    }

    if (!this.form.valid) {
      return;
    }

    const cellphone = this.form.value.cellphone;
    const code = this.form.value.code;

    this.rs.register(cellphone, code).then(x => {
      if (x.status === 0) {
        this.rs.cellphone = cellphone;
        this.rs.isVerified = true;
        this.r.navigate(['/signup']).catch(err => { console.log('message:', err); });
      } else {
        this.msg.error(x.message);
      }
    });
  }

  sendCode(event: Event): void {
    event.preventDefault();

    this.form.controls['cellphone'].markAsDirty();
    this.form.controls['cellphone'].updateValueAndValidity();

    if (this.form.controls['cellphone'].invalid) {
      return;
    }

    this.rs.sendCode(this.form.value.cellphone).then(x => {
      if (x.status === 0) {
        this.msg.success('验证码已经发送，请查收');
        this.isVerified = true;
      } else {
        this.msg.error('这个号码已经注册了');
        console.log(x.message);
      }
    });
  }
}
