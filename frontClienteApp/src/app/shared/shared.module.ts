import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material.module';

const MODULES = [MaterialModule]
const COMPONENTS = []

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ...MODULES
  ],
  exports:[
    ...MODULES
  ]
})
export class SharedModule { }
