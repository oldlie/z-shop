import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { MainNavigationComponent } from './widget/main-navigation/main-navigation.component';
import { MainAsideComponent } from './widget/main-aside/main-aside.component';
import { HomeComponent } from './pages/home/home.component';
import { CommodityComponent } from './pages/shop/commodity/commodity.component';
import { AddCommodityComponent } from './pages/shop/add-commodity/add-commodity.component';
import { SpecComponent } from './pages/shop/spec/spec.component';
import { AddComponent } from './pages/shop/spec/add/add.component';
import { ListComponent } from './pages/shop/spec/list/list.component';
import { NavigationComponent } from './pages/navigation/navigation.component';
import { AddComponent as NavAddComponent } from './pages/navigation/add/add.component';
import { ListComponent as NavListComponent } from './pages/navigation/list/list.component';
import { AddArticleComponent } from './pages/article/add-article/add-article.component';
import { ListArticleComponent } from './pages/article/list-article/list-article.component';
import { CoreService } from './services/core.service';
import { TagComponent } from './pages/tag/tag.component';
import { MenuComponent } from './pages/shop/menu/menu.component';
import { PaginationComponent } from './widget/pagination/pagination.component';
import { HttpClientModule } from '@angular/common/http';
import { IndexComponent as ShopIndexComponent } from './pages/shop/index/index.component';
import { IndexComponent as ArticleIndexComponent } from './pages/article/index/index.component';
import { ArticleMenuComponent } from './pages/article/menu/menu.component';
import { TagActiveDirective } from './directive/tag-active.directive';
import { CarouselComponent } from './pages/home/carousel/carousel.component';

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
    ListComponent,
    NavigationComponent,
    NavAddComponent,
    NavListComponent,
    AddArticleComponent,
    ListArticleComponent,
    TagComponent,
    MenuComponent,
    PaginationComponent,
    ShopIndexComponent,
    ArticleIndexComponent,
    ArticleMenuComponent,
    TagActiveDirective,
    CarouselComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    AppRoutingModule,
    NgZorroAntdModule.forRoot(),
    HttpClientModule,
  ],
  providers: [
    CoreService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
