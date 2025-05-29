package com.example.AppFitness.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_exercicios")
public class Exercicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, max = 100, message = "O atributo nome deve ter no minimo 5 e no máximo 100 caracteres.")
	@NotBlank
	private String nome;
	
	@NotNull
	private int carga;
	
	@Positive(message = "Repetições deve ser maior do que zero!")
	@NotBlank
	private int repeticoes;
	
	@Positive(message = "A serie deve ser maior do que zero!")
	private int series;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCarga() {
		return carga;
	}
	public void setCarga(int carga) {
		this.carga = carga;
	}
	public int getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
}
