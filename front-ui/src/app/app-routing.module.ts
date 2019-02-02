import { NgModule } from '@angular/core';
import { RouterModule, } from '@angular/router';
import { PageNoteFoundComponent } from './pages/page-note-found/page-note-found.component';
import { HomeComponent } from './pages/home/home.component';
import { CommodityComponent } from './pages/commodity/commodity.component';
import { ArticleComponent } from './pages/article/article.component';
import { CommodityListComponent } from './pages/commodity-list/commodity-list.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { SignupComponent } from './pages/signup/signup.component';
import { ShoppingCartComponent } from './pages/shopping-cart/shopping-cart.component';
import { SettlementComponent } from './pages/settlement/settlement.component';

const appRoutes = [
    { path: '', component: HomeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'article/:id', component: ArticleComponent },
    { path: 'commodity/list', component: CommodityListComponent },
    { path: 'commodity/detail/:id', component: CommodityComponent },
    { path: 'login', component: LoginComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'signup', component: SignupComponent },
    { path: 'shopping/cart', component: ShoppingCartComponent },
    { path: 'settlement', component: SettlementComponent},
    { path: '**', component: PageNoteFoundComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule { }
