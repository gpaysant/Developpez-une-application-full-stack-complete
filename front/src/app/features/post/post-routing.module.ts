import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './components/list/list.component';
import { DetailComponent } from './components/detail/detail.component';
import { FormComponent } from './components/form/form.component';


const routes: Routes = [
  { path: '', title: "Posts", component: ListComponent }, 
  { path: 'detail/:id', title: 'Post - detail', component: DetailComponent },
  { path: 'create', title: 'Post - create', component: FormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostRoutingModule { }