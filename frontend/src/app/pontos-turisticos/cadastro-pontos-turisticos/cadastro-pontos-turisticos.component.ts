import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';
import { triggerFormValidators } from 'src/app/shared/util';

@Component({
  selector: 'app-cadastro-pontos-turisticos',
  templateUrl: './cadastro-pontos-turisticos.component.html',
  styleUrls: ['./cadastro-pontos-turisticos.component.css']
})
export class CadastroPontosTuristicosComponent implements OnInit {

	options: Array<Pais> = [];
	idPontoTuristico: string;
	formPT: FormGroup;
	title: string = "Novo cadastro de Ponto Turistico"
	constructor(private formBuilder: FormBuilder,
		private httpService: HttpService,
		private poNotification: PoNotificationService,
		private route: ActivatedRoute,
		private router: Router,
		private http: HttpService
		) { 
		
		this.formPT = this.formBuilder.group({
      		pais: ['', Validators.compose([Validators.required])],
      		cidade: ['', Validators.compose([Validators.required])],
			nome: ['', Validators.compose([Validators.required])],
			estacao: ['', Validators.compose([Validators.required])],
			resumo: ['', Validators.compose([Validators.required])],
		})
	}

	ngOnInit(): void {
		this.carregarPaises()
		this.idPontoTuristico = this.route.snapshot.paramMap.get('idPontoTuristico');
		if (this.idPontoTuristico !== ''){
			this.buscaDadosPT()
			this.title = "Alteração do Ponto Turistico"
		}
	}
/*
	carregarPaises() {
		this.httpService.get('pais').subscribe((resposta: any[]) => {
		  this.options = resposta.map(item => ({ nome: item.nome }));
		});

	  }
*/

	carregarPaises(){
		return this.httpService.get('pais').subscribe({
			next: (resposta)=>{
				let registros: Array<Pais> = []
				resposta.forEach(item => {
					let novoPais: Pais = {
						nome: item.nome,
					}
					registros.push(novoPais);
				});

				this.options = [...registros]
			}
		})
	}

	salvar(){
		if (this.validarRegistro()){
			if (this.idPontoTuristico === ''){
				this.enviarPost()
			} else {
				this.enviarPut()
			}
		} else {
			this.poNotification.error("Preencha todos os campos antes de salvar as alterações!")
		}
	}

	voltar(){
		this.router.navigate(['/ponto-turistico'], { relativeTo: this.route })
	}

	validarRegistro(): boolean{
		return this.formPT.valid;
	}

	enviarPost(){
		this.http.post('ponto-turistico',this.formPT.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro criado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

	enviarPut(){
		this.http.put('ponto-turistico/' + this.idPontoTuristico,this.formPT.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

	buscaDadosPT(){
		this.http.get('ponto-turistico/' + this.idPontoTuristico).subscribe({
			next: (resposta)=>{
				this.formPT.patchValue({
          		pais: resposta.pais,
          		cidade: resposta.cidade,
          		nome: resposta.nome,
          		estacao: resposta.estacao,
          		resumo: resposta.resumo
				})
			},
			error: (erro)=>{
				this.poNotification.error(erro)
			}
		})
	}
}

interface Pais{
	nome: string,
}