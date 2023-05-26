import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoTableAction, PoTableColumn } from '@po-ui/ng-components';
import { map, tap } from 'rxjs';
import { HttpService } from '../service/http-service.service';

@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css']
})
export class ComentariosComponent implements OnInit {

  lsActions: Array<PoTableAction> = this.carregarActions();
	lsColumns: Array<PoTableColumn> = this.carregarColunas();
	lsComentarios: Array<Comentario> = []

	constructor(
		private httpService: HttpService,
		private poNotification: PoNotificationService,
		private router: Router,
		private activatedRoute: ActivatedRoute) { }

	ngOnInit(): void {
		this.carregarComentarios()
	}

	carregarActions(): Array<PoTableAction> {
		return [
			{
				label: 'Excluir',
				icon: 'po-icon-delete',
				action: (row: Comentario)=>{ this.deletarCadastro(row.id) }
			}
		]
	}

	deletarCadastro(id: string): void {
		this.httpService.delete('comentario/' + id).subscribe({
			next: (response)=>{
				this.poNotification.success('Registro excluido com sucesso!');
				this.carregarComentarios();
			},
			error: (error)=>{
				this.poNotification.error(error);
			}
		})
	}

	navegarParaCadastro(codigoCom: string = ""){
		this.router.navigate(['cadastro', codigoCom], { relativeTo: this.activatedRoute })
	}

	/*
	carregarComentarios(){
		return this.httpService.get('comentario/{idPT}/idPT').subscribe({
			next: (resposta)=>{
				let registros: Array<Comentario> = []
				resposta.forEach(item => {
					let novoComentario: Comentario = {
						id: item.id,
            			idPT : item.idPT,
						nome: item.nome,
						comentario: item.comentario,
						data: item.data
					}
					registros.push(novoComentario);
				});

				this.lsComentarios = [...registros]
			}
		})
	}*/

	carregarComentarios() {
		this.activatedRoute.queryParams.subscribe(params => {
		  const idPontoTuristico = params['idPontoTuristico'];
	  
		  this.httpService.get(`comentario/${idPontoTuristico}/idPT`).subscribe({
			next: (resposta) => {
			  let registros: Array<Comentario> = [];
			  resposta.forEach(item => {
				let novoComentario: Comentario = {
				  id: item.id,
				  idPT: idPontoTuristico,
				  nome: item.nome,
				  comen: item.comen,
				  data: item.data
				};
				registros.push(novoComentario);
			  });
	  
			  this.lsComentarios = [...registros];
			}
		  });
		});
	  }

	carregarColunas(): Array<PoTableColumn>{
		return [
			{
				property: 'nome',
				label: 'Nome'
			},
			{
				property: 'comen',
				label: 'Comentario',
			},
			{
				property: 'data',
				label: 'Data'
			},
		]
	}
}

interface Comentario{
	id: string,
	idPT: string,
	nome: string,
	comen: string,
	data: string
}
