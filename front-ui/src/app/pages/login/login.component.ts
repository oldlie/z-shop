import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CoreService } from '../../service/core.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validateForm: FormGroup;

  constructor(private coreService: CoreService, private fb: FormBuilder,
    private msg: NzMessageService,
    private r: Router) { }

  ngOnInit() {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  submitForm(): void {
    for (const key in this.validateForm.controls) {
      if (this.validateForm.controls.hasOwnProperty(key)) {
        this.validateForm.controls[key].markAsDirty();
        this.validateForm.controls[key].updateValueAndValidity();
      }
    }

    if (!this.validateForm.valid) {
      return;
    }
    console.log(this.validateForm.value.userName);
    this.coreService.login(this.validateForm.value.userName,
      this.validateForm.value.password).then(x => {
        if (x.status === 0) {
          this.r.navigate(['/']).catch(e => {
            console.log('login navigate error:', e);
          });
        } else {
          this.msg.error('用户名或者密码不正确');
          console.log(x.message);
        }
        return this.validateForm.value.userName;
      }).then((userName) => {
        this.coreService.initProfile(userName);
      });
  }
}
