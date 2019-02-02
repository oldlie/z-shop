import {Component, forwardRef, Inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import { ValidateResult } from '../../response/response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [
    LoginService
  ]
})
export class LoginComponent implements OnInit {

  validateForm: FormGroup;
  allowSubmit: boolean;
  username: string;
  password: string;

  constructor(@Inject(forwardRef(() => FormBuilder)) private formBuilder: FormBuilder,
              private loginService: LoginService,
              private router: Router) { }

  ngOnInit() {
    this.allowSubmit = false;
    this.validateForm = this.formBuilder.group({
      password: [ this.password, [Validators.required]],
      username: [ this.username, [Validators.required]],
    });

    this.test();
  }

  test() {
    this.loginService.login(this.validateForm.get('username').value, this.validateForm.get('password').value)
      .then(response => {
        if (response.status === 0) {
          this.router.navigate(['/payment']).catch(err => {
            console.log('navigate to dashboard', err);
          });
        }
      });
  }

 submit() {
   if (this.validateForm.controls['mail'].hasError('message') ||
    this.validateForm.controls['password'].hasError('message')) {
      return;
    }
    this.loginService.login(this.validateForm.get('mail').value, this.validateForm.get('password').value)
      .then(response => {
        if (response.status === 0) {
          this.router.navigate(['/home']).catch(err => {
            console.log('navigate to dashboard', err);
          });
        }
      });
  }

  reset() {
    this.validateForm.reset();
  }

  ctrl(item: string): AbstractControl {
    return this.validateForm.controls[item];
  }

  statusCtrl(item: string): string | void {
    if (!this.validateForm.controls[item]) {
      return;
    }
    const control: AbstractControl = this.validateForm.controls[item];
    return control.dirty && control.hasError('status') ? control.errors.toLocaleString() : '';
  }

  messageCtrl(item: string): string | void {
    if (!this.validateForm.controls[item])  {
      return;
    }
    const control: AbstractControl = this.validateForm.controls[item];
    return control.dirty && control.hasError('message') ? control.errors.message : '';
  }

  private emailValidator = (control: FormControl): ValidateResult => {
    const mailReg: RegExp = /^[A-Za-z0-9一-龥]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if (!mailReg.test(control.value)) {
      return { status: 'error', message: '邮箱格式不正确' };
    }
    return { status: 'success' };
  }

  private passwordValidator = (control: FormControl): {[key: string]: any} => {
    if (!control.value) {
      return { status: 'error', message: '密码是必填的' };
    }
    if (control.value.length < 6) {
      return { status: 'error', message: '密码长度必须大于 6 位' };
    }
    return { status: 'success' };
  }

}
