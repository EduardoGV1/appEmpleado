import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export abstract class BaseService {
  protected obtenerHeaders(): HttpHeaders {
    let headers = new HttpHeaders()
                      .set('Content-Type', 'application/json')
                      .set('Accept', 'application/json')
                      .set('Access-Control-Allow-Headers', 'Content-Type')
                      .set('Access-Control-Allow-Methods', '*')
                      .set('Access-Control-Allow-Origin', '*')
    return headers;
  }


}
