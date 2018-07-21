import { NgModule } from '@angular/core';
import { RouterModule, } from '@angular/router';
import { PageNoteFoundComponent } from './pages/page-note-found/page-note-found.component';
import { HomeComponent } from './pages/home/home.component';
import { CommodityComponent } from './pages/commodity/commodity.component';
import { ArticleComponent } from './pages/article/article.component';
import { CommodityListComponent } from './pages/commodity-list/commodity-list.component';

const appRoutes = [
    {path: '', component: HomeComponent},
    {path: 'home', component: HomeComponent},
    {path: 'article/:id', component: ArticleComponent},
    {path: 'commodity/list', component: CommodityListComponent},
    {path: 'commodity/detail/:id', component: CommodityComponent},
    {path: '**', component: PageNoteFoundComponent}
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {}
