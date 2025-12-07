// src/app/components/body/body.ts
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AgGridModule } from 'ag-grid-angular';

@Component({
    selector: 'app-body',
    standalone: true,
    templateUrl: './body.html',
    styleUrls: ['./body.css'],
    imports: [RouterModule, AgGridModule]
})
export class Body {
    
}
