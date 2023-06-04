import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CsvServiceService {

  constructor(private http: HttpClient) { }


  // loadSummary(): void {
  //   this.http.get<any[]>("http://localhost:8080/summary")
  //     .subscribe(
  //       data => {
  //         this.sumaryList = data;
  //         console.log(this.sumaryList)
  //       }, error => {
  //         console.error("Ocorreu um erro", error)
  //       }
  //     )
  // }
}
