import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../../register.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
    private r: Router,
    private rs: RegisterService) { }

  ngOnInit() {
    if (!this.rs.isVerified) {
      this.r.navigate(['/login']).catch(e => console.error(e));
      return;
    }
    const cellphone = this.rs.cellphone;
    this.form = this.fb.group({
      nickname: [null],
      password: [null, [Validators.required, Validators.max(32), Validators.min(8)]],
      password2: [null, [Validators.required, Validators.max(32), Validators.min(8)]],
      image: ['/user.jpg'],
    });
  }

  submitForm() {
    
  }
}
