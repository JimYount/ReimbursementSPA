import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { PokemonComponent } from './pokemon/pokemon.component';
import { StatsComponent } from './stats/stats.component';
import { HttpClientModule } from '@angular/common/http';
import { PokemonService } from './pokemon.service';
import { SpritesComponent } from './sprites/sprites.component';
import { HighlightDirective } from './highlight.directive';

@NgModule({
  declarations: [
    // This array declares all the Components that are used in the app
    AppComponent,
    PokemonComponent,
    StatsComponent,
    SpritesComponent,
    HighlightDirective
  ],
  imports: [
    // This array imports other modules used in the app
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    // This array will PROVIDE an instance of the following classes
    // to any component that NEEDS one.
    PokemonService
  ],
  bootstrap: [
    // This array creates the following components when the app is started
    AppComponent
  ]
})
export class AppModule { }
