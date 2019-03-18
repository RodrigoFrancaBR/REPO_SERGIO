import { AdminComponent } from './views/admin/admin.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

// Import Containers
import { DefaultLayoutComponent } from './containers';
import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';

import { LoginModule } from './views/login/login.module';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { OperadorComponent } from './views/operador/operador.component';
import { PadraoComponent } from './views/padrao/padrao.component';


export const routes: Routes = [
  // {
  //   path: '',
  //   redirectTo: 'dashboard',
  //   pathMatch: 'full',
  // },

  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  // {
  //   path: '500',
  //   component: P500Component,
  //   data: {
  //     title: 'Page 500'
  //   }
  // },
  {
    path: '',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  // Para fins de teste, caso o usuário use o nome como rota. 
  // {
  //   path: 'usuario/:name',
  //   component: PadraoComponent,
  // },
  {
    path: 'usuario/admin',
    component: AdminComponent,
  },
  {
    path: 'usuario/operador',
    component: OperadorComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  // {
  //   path: '',
  //   component: DefaultLayoutComponent,
  //   data: {
  //     title: 'Home'
  //   },
  //   children: [
  //     {
  //       path: 'base',
  //       loadChildren: './views/base/base.module#BaseModule'
  //     },
  //     {
  //       path: 'buttons',
  //       loadChildren: './views/buttons/buttons.module#ButtonsModule'
  //     },
  //     {
  //       path: 'charts',
  //       loadChildren: './views/chartjs/chartjs.module#ChartJSModule'
  //     },
  //     {
  //       path: 'dashboard',
  //       loadChildren: './views/dashboard/dashboard.module#DashboardModule'
  //     },
  //     {
  //       path: 'icons',
  //       loadChildren: './views/icons/icons.module#IconsModule'
  //     },
  //     {
  //       path: 'notifications',
  //       loadChildren: './views/notifications/notifications.module#NotificationsModule'
  //     },
  //     {
  //       path: 'theme',
  //       loadChildren: './views/theme/theme.module#ThemeModule'
  //     },
  //     {
  //       path: 'widgets',
  //       loadChildren: './views/widgets/widgets.module#WidgetsModule'
  //     }
  //   ]
  // },
  { path: '**', component: P404Component }
];

@NgModule({
  imports: [CommonModule, LoginModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
