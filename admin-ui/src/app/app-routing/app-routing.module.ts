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

const appRoutes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'commodity/index', component: CommodityComponent},
  {path: 'commodity/menu', component: CommodityMenuComponent},
  {path: 'commodity/item/add', component: AddCommodityComponent},
  {path: 'commodity/spec', component: SpecComponent},
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
