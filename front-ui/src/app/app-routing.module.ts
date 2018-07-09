import { NgModule } from '@angular/core';
import { RouterModule, } from '@angular/router';
import { PageNoteFoundComponent } from './pages/page-note-found/page-note-found.component';
import { HomeComponent } from './pages/home/home.component';
import { CommodityComponent } from './pages/commodity/commodity.component';

const appRoutes = [
    {path: 'home', component: HomeComponent},
    {path: 'commodity/:id', component: CommodityComponent},
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
