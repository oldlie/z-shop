import { NgModule } from '@angular/core';
import { RouterModule, } from '@angular/router';
import { LoginComponent } from '../pages/login/login.component';
import { PageNotFoundComponent } from '../pages/page-not-found/page-not-found.component';
import { HomeComponent } from '../pages/home/home.component';
import { CommodityComponent } from '../pages/shop/commodity/commodity.component';
import { AddCommodityComponent } from '../pages/shop/add-commodity/add-commodity.component';
import { SpecComponent } from '../pages/shop/spec/spec.component';
import { NavigationComponent } from '../pages/navigation/navigation.component';
import { TagComponent } from '../pages/tag/tag.component';
import { MenuComponent as CommodityMenuComponent } from '../pages/shop/menu/menu.component';
import { IndexComponent as CommodityIndexComponent } from '../pages/shop/index/index.component';
import { IndexComponent as ArticleIndexComponent } from '../pages/article/index/index.component';

const appRoutes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'article/index', component: ArticleIndexComponent},
  {path: 'commodity/index', component: CommodityIndexComponent},
  {path: 'commodity/menu', component: CommodityMenuComponent},
  {path: 'commodity/add', component: AddCommodityComponent},
  {path: 'commodity/spec', component: SpecComponent},
  {path: 'commodity/edit', component: AddCommodityComponent},
  {path: 'navigation/:t', component: NavigationComponent},
  {path: 'tag', component: TagComponent},
  { path: '',   redirectTo: '/login', pathMatch: 'full' },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [
   RouterModule.forRoot(
     appRoutes
   )
  ],
  declarations: [],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
