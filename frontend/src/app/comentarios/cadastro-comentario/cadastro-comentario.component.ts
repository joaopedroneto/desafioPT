import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';
import { triggerFormValidators } from 'src/app/shared/util';

@Component({
  selector: 'app-cadastro-comentario',
  templateUrl: './cadastro-comentario.component.html',
  styleUrls: ['./cadastro-comentario.component.css']
})
export class CadastroComentarioComponent implements OnInit {
  idComentario: string;
  idPontoTuristico: string;
  Data: string;
	formCom: FormGroup;
	title: string = "Novo comentario"
	constructor(private formBuilder: FormBuilder,
		private poNotification: PoNotificationService,
		private route: ActivatedRoute,
		private router: Router,
		private http: HttpService
		) { 

		this.formCom = this.formBuilder.group({
			nome: ['', Validators.compose([Validators.required])],
			comen: ['', Validators.compose([Validators.required])]
		})
	}

	ngOnInit(): void {
		const currentDate = new Date();
		this.Data = currentDate.toLocaleString();
		this.idComentario = this.route.snapshot.paramMap.get('idComentario');
		this.idPontoTuristico = this.getIdPontoTuristicoFromParentRoute();
		if (this.idComentario !== ''){
			this.buscaDadosComentario()
			this.title = "Alteração do Comentario"
		}
	}

	salvar(){
		if (this.validarRegistro()){
			if (this.idComentario === ''){
				this.enviarPost()
			} else {
				this.enviarPut()
			}
		} else {
			this.poNotification.error("Preencha todos os campos antes de salvar as alterações!")
		}
	}

	voltar() {
		const codigoPT = this.idPontoTuristico;
		this.router.navigate(['ponto-turistico', 'cadastro', codigoPT, 'comentario'], { queryParams: { idPontoTuristico: this.idPontoTuristico } });
	}

	validarRegistro(): boolean{
		return this.formCom.valid;
	}

	enviarPost() {
		const comentario = {
		  ...this.formCom.value,
		  idPT: this.idPontoTuristico,
		  data: this.Data
		};
	
		this.http.post('comentario', comentario).subscribe({
		  next: (resposta) => {
			this.poNotification.success("Registro criado com sucesso!");
			this.voltar();
		  },
		  error: (erro) => {
			this.poNotification.error(erro);
		  },
		});
	  }
	
	enviarPut(){
		this.http.put('comentario/' + this.idComentario,this.formCom.value).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

	getIdPontoTuristicoFromParentRoute(): string {
		let parentRoute = this.route.parent;
		while (parentRoute) {
		  const idPontoTuristico = parentRoute.snapshot.paramMap.get('idPontoTuristico');
		  if (idPontoTuristico) {
			return idPontoTuristico;
		  }
		  parentRoute = parentRoute.parent;
		}
		return null;
	  }
/*
	buscaDadosComentario() {
		this.route.queryParams.subscribe(params => {
		  const idPontoTuristico = params['idPontoTuristico'];
	  
		  this.http.get('comentario/' + this.idComentario).subscribe({
			next: (resposta) => {
			  this.formCom.patchValue({
				nome: resposta.nome,
				idPT: idPontoTuristico, // Assign idPontoTuristico to idPT
				comen: resposta.comen,
				data: resposta.data
			  });
			},
			error: (erro) => {
			  this.poNotification.error(erro);
			}
		  });
		});
	  }
*/
buscaDadosComentario() {
	this.http.get('comentario/' + this.idComentario).subscribe({
	  next: (resposta) => {
		const idPontoTuristico = resposta.idPT;
  
		if (!idPontoTuristico) {
		  this.poNotification.error("Missing idPontoTuristico value");
		  return;
		}
  
		this.formCom.patchValue({
		  idPT: idPontoTuristico,
		  nome: resposta.nome,
		  comen: resposta.comen,
		  data: resposta.data
		});
	  },
	  error: (erro) => {
		this.poNotification.error(erro);
	  }
	});
  }

}

