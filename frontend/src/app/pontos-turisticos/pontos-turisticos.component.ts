import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoTableAction, PoTableColumn } from '@po-ui/ng-components';
import { map, tap } from 'rxjs';
import { HttpService } from '../service/http-service.service';
import { ComentariosComponent } from '../comentarios/comentarios.component';



@Component({
  selector: 'app-pontos-turisticos',
  templateUrl: './pontos-turisticos.component.html',
  styleUrls: ['./pontos-turisticos.component.css']
})
export class PontosTuristicosComponent implements OnInit {

  	lsActions: Array<PoTableAction> = this.carregarActions();
	lsColumns: Array<PoTableColumn> = this.carregarColunas();
	lsPTses: Array<PT> = []

	constructor(
		private httpService: HttpService,
		private poNotification: PoNotificationService,
		private router: Router,
		private activatedRoute: ActivatedRoute) { }

	ngOnInit(): void {
		this.carregarPTses()
	}

	carregarActions(): Array<PoTableAction> {
		return [
			{
				label: 'Comentarios',
				icon: 'po-icon-sms',
				action: (row: PT)=>{ this.navegarParaComentarios(row.id) }
			},
			{
				label: 'Editar',
				icon: 'po-icon-edit',
				action: (row: PT)=>{ this.navegarParaPT(row.id) }
			},
			{
				label: 'Excluir',
				icon: 'po-icon-delete',
				action: (row: PT)=>{ this.deletarCadastro(row.id) }
			}
		]
	}

	deletarCadastro(id: string): void {
		this.httpService.delete('ponto-turistico/' + id).subscribe({
			next: (response)=>{
				this.poNotification.success('Registro excluido com sucesso!');
				this.carregarPTses();
			},
			error: (error)=>{
				this.poNotification.error(error);
			}
		})
	}

	navegarParaComentarios(codigoPT: string = "") {
		this.router.navigate(['cadastro', codigoPT, 'comentario'], { relativeTo: this.activatedRoute, queryParams: { idPontoTuristico: codigoPT } });
	  }

	navegarParaPT(codigoPT: string = ""){
		this.router.navigate(['cadastro', codigoPT], { relativeTo: this.activatedRoute })
	}

	carregarPTses(){
		return this.httpService.get('ponto-turistico').subscribe({
			next: (resposta)=>{
				let registros: Array<PT> = []
				resposta.forEach(item => {
					let novoPT: PT = {
						id: item.id,
						pais : item.pais,
						cidade : item.cidade,
						codPT: item.nome,
						codEsta: item.estacao,
						codResu: item.resumo,
					}
					registros.push(novoPT);
				});

				this.lsPTses = [...registros]
			}
		})
	}

	carregarColunas(): Array<PoTableColumn>{
		return [
			{
				property: 'pais',
				label: 'Pais'
			},
			{
				property: 'cidade',
				label: 'Cidade',
			},
			{
				property: 'codPT',
				label: 'Ponto Turistico'
			},
			{
				property: 'codEsta',
				label: 'Estação'
			},
			{
				property: 'codResu',
				label: 'Resumo'
			}
		]
	}
}

interface PT{
	id : string,
	pais: string,
	cidade: string,
	codPT: string,
	codEsta: string,
	codResu: string,
}



  
  //constructor() { }

  //ngOnInit(): void {
  //}
