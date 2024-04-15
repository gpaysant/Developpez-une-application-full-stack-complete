import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.gard';
import { UnauthGuard } from './guards/unauth.gard';

const routes: Routes = [
  {
    path: '',
    canActivate: [UnauthGuard],
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  }, 
  {
    path: 'post',
    canActivate: [AuthGuard],
    loadChildren: () => import('./features/post/post.module').then(m => m.PostModule)
  }, 
  {
    path: 'topic',
    canActivate: [AuthGuard],
    loadChildren: () => import('./features/topic/topic.module').then(m => m.TopicModule)
  },
  {
    path: 'user',
    canActivate: [AuthGuard],
    loadChildren: () => import('./features/user/user.module').then(m => m.UserModule)
  },
  {
    path: '**',
    canActivate: [AuthGuard],
    redirectTo: ''
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}