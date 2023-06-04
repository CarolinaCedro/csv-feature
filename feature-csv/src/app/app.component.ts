import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  sumaryList: any = []

  form: FormGroup
  selectedValues: string[] = [];
  isGenerated: boolean = false;
  openGenerated: boolean = true;

  durationInSeconds = 5;

  constructor(private http: HttpClient, private formBuilder: FormBuilder, private _snackBar: MatSnackBar) {
    this.form = this.formBuilder.group({})
  }

  ngOnInit(): void {
    this.clearValuesSelectedValues()
    this.loadSummary()
    this.form.valueChanges.subscribe((values) => {
      this.selectedValues = Object.keys(values).filter(key => values[key]);
    });
  }

  loadSummary(): void {
    this.http.get<any[]>("http://localhost:8080/summary")
      .subscribe(
        data => {
          this.sumaryList = data;
        }, error => {
          console.error("Ocorreu um erro", error)
        }
      )
  }

  openSelectedFields() {
    this.isGenerated = true;
    this.openGenerated = false;
  }
  
  generatedCsv() {
    const data = {
      initialDate: "02/05/2023",
      finalDate: "02/06/2023",
      delimiter: ";",
      headers: this.getSelectedValues()
    };

    this.http.post("http://localhost:8080/summary/convertList", data, {responseType: 'text'})
      .subscribe(response => {
        // Cria um elemento <a> para simular o clique no link de download
        const link = document.createElement('a');
        link.href = 'data:text/csv;charset=utf-8,' + encodeURIComponent(response);
        link.download = 'arquivo.csv';

        // Simula o clique no link de download
        link.click();
        this.isGenerated = false;
        this.openGenerated = true;
        this.clearValuesSelectedValues();
        this.openSnackBar("Tudo certo patroa")

      }, error => {
        console.error(error);
      });
  }

  openSnackBar(msg: string) {
    this._snackBar.open(msg, 'Fechar', {
      duration: 2000,
      horizontalPosition: 'end',
      verticalPosition: 'top'
    });
  }

  clearValuesSelectedValues() {
    this.selectedValues = []
    this.form = this.formBuilder.group({
      conta: false,
      ciclo: false,
      contato: false,
      cod_conta: false,
      name_collaborator: false,
      valor: false,
      tp_despesa: false,
      tp_combustivel: false,
      tp_document: false,
      qtde: false,
      tp_consolidacao: false,
      observacao: false,
      registro: false,
      date: false
    });
  }


  getSelectedValues(): Array<string> {
    // Obtém as chaves (propriedades) do objeto form.value e realiza as seguintes operações:
    return this.selectedValues = Object.keys(this.form.value)
      // Filtra as chaves com base em uma condição, verificando se o valor correspondente à chave é verdadeiro (checkbox marcado)
      .filter(key => this.form.value[key])
      // Mapeia cada elemento filtrado para outro valor, que no caso é a própria chave
      .map(key => key);

  }


}
