import {Component, forwardRef, Inject, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ValidateResult} from '../common/validate-result';
import {LoginService} from '../services/login.service';
import {Router} from '@angular/router';

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

  constructor(@Inject(forwardRef(() => FormBuilder)) private formBuilder: FormBuilder,
              private loginService: LoginService,
              private router: Router) { }

  ngOnInit() {
    this.validateForm = this.formBuilder.group({
      password: [ '', [this.passwordValidator] ],
      mail: [ '', [this.emailValidator] ],
    });
  }

  submit() {
    console.log(this.validateForm.value);
    if (this.validateForm.valid) {
      this.loginService.login(this.validateForm.get('mail').value, this.validateForm.get('password').value)
        .then(response => {
          if (response.status === 0) {
            this.router.navigate(['/dashboard']).catch(err => {
              console.log('navigate to dashboard', err);
            });
          }
        });
    }
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
    const mailReg: RegExp = /^[A-Za-z0-9一-龥]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
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
