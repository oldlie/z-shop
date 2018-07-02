import { NgModule } from '@angular/core';
import { RouterModule, } from '@angular/router';
import { PageNoteFoundComponent } from './pages/page-note-found/page-note-found.component';
import { HomeComponent } from './pages/home/home.component';

const appRoutes = [
    {path: 'home', component: HomeComponent},
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
