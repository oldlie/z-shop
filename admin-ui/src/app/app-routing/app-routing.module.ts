import { NgModule } from '@angular/core';
import { RouterModule, } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';
import { HomeComponent } from '../home/home.component';
import { CommodityComponent } from '../shop/commodity/commodity.component';
import { AddCommodityComponent } from '../shop/add-commodity/add-commodity.component';
import { SpecComponent } from '../shop/spec/spec.component';
import { NavigationComponent } from '../navigation/navigation.component';

const appRoutes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'commodity/index', component: CommodityComponent},
  {path: 'commodity/item/add', component: AddCommodityComponent},
  {path: 'commodity/spec', component: SpecComponent},
  {path: 'navigation/:t', component: NavigationComponent},
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
