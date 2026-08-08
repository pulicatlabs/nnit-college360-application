import {Routes} from '@angular/router';
import {DashboardComponent} from './dashboard.component';
import {ModuleComponent} from './module.component';
export const routes:Routes=[{path:'',component:DashboardComponent},{path:'dashboard',component:DashboardComponent},{path:'module/:key',component:ModuleComponent},{path:'**',redirectTo:''}];
