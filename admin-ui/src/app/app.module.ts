import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ElModule } from 'element-angular';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import {ReactiveFormsModule} from '@angular/forms';
import { MainNavigationComponent } from './widget/main-navigation/main-navigation.component';
import { MainAsideComponent } from './widget/main-aside/main-aside.component';
import { HomeComponent } from './home/home.component';
import { CommodityComponent } from './shop/commodity/commodity.component';
import { AddCommodityComponent } from './shop/add-commodity/add-commodity.component';
import { SpecComponent } from './shop/spec/spec.component';
import { AddComponent } from './shop/spec/add/add.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PageNotFoundComponent,
    MainNavigationComponent,
    MainAsideComponent,
    HomeComponent,
    CommodityComponent,
    AddCommodityComponent,
    SpecComponent,
    AddComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    ElModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
