package br.com.contaazul.teste.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.contaazul.teste.exception.ComandoInvalidoException;
import br.com.contaazul.teste.service.MoverRoboService;

@Controller
@RequestMapping("/rest/mars")
public class ComandosController {

	@Autowired
	private MoverRoboService service;

	@PostMapping("/{comandos}")
	public ResponseEntity<Object> executarComandos(@PathVariable String comandos) {
		try {
			String retorno = service.executarComandos(comandos);
			return new ResponseEntity<>(retorno, HttpStatus.OK);
		} catch (ComandoInvalidoException e) {
			Logger.getLogger(getClass().getName()).warning(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("posicaoFinal")
	public ResponseEntity<Object> getPosicaoFinal() {
		String retorno = service.getPosicaoFinal();
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	}
}