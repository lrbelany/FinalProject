import { LinksComponent } from './components/links/links.component';
import { TeamsearchComponent } from './components/teamsearch/teamsearch.component';
import { MeetupComponent } from './components/meetup/meetup.component';

import { OtherUserComponent } from './components/other-user/other-user.component';
import { UserPostComponent } from './components/user-post/user-post.component';

import { AppComponent } from './app.component';
import { IncompletePipe } from './pipes/incomplete.pipe';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './components/account/account.component';
import { AlertsComponent } from './components/alerts/alerts.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { MygroupsComponent } from './components/mygroups/mygroups.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { DatePipe } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';
import { SearchComponent } from './components/search/search.component';
import { NewsfeedComponent } from './components/newsfeed/newsfeed.component';
import { TeamComponent } from './components/team/team.component';



const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'account', component: AccountComponent},
  {path: 'myGroups', component: MygroupsComponent},
  {path: 'alerts', component: AlertsComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'search', component: SearchComponent},
  {path: 'newsfeed', component: NewsfeedComponent},
  {path: 'user-post', component: UserPostComponent},
  {path: 'post', component: UserPostComponent},
  {path: 'other/:id', component: OtherUserComponent},
  {path: 'team', component: TeamComponent},
  {path: 'meetup', component: MeetupComponent},
  {path: 'teamsearch', component: TeamsearchComponent},
  {path: 'resources', component: LinksComponent},
  {path: '**', component: NotfoundComponent}
];

@NgModule({


  imports: [RouterModule.forRoot(routes, {useHash: true})],

providers:[DatePipe, IncompletePipe],
bootstrap: [AppComponent],
  exports: [RouterModule]
})
export class AppRoutingModule { }
