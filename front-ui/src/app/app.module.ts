import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { HomeComponent } from './pages/home/home.component';
import { PageNoteFoundComponent } from './pages/page-note-found/page-note-found.component';
import { AppRoutingModule } from './app-routing.module';
import { CoreService } from './service/core.service';
import { CommodityComponent } from './pages/commodity/commodity.component';
import { ArticleComponent } from './pages/article/article.component';
import { CommodityListComponent } from './pages/commodity-list/commodity-list.component';

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PageNoteFoundComponent,
    CommodityComponent,
    ArticleComponent,
    CommodityListComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    NgZorroAntdModule
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN }],
  bootstrap: [AppComponent]
})
export class AppModule { }
