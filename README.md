# Sistema Academico
Implementação de um [projeto](https://drive.google.com/file/d/1ebk_N6R4XshUrAeKfebpPzf-tdvT6ZxL/view?usp=sharing) de sistema acadêmico de matrícula de alunos para a matéria de Teste de Software ministrada pelo Profº. Drº. [Willy Tiengo](https://sites.google.com/site/willytiengo/) - 2018.2.

## Tecnologias utilizadas
1. **Java 8:** back-end.
2. **Maven:** gerenciamento e automação de construção (build) de projetos.
3. **JUnit 5:** Testes unitários.
4. **MySQL:** banco de dados relacional.
5. **Hibernate:** persistência de dados de BD relacional.

**Entidades principais:** 
* Aluno
* Professor 
* Disciplina
* Curso
* Secretaria
* Departamento

**Grau de complexidade:** aluno -> disciplina -> professor -> curso -> secretaria -> departamento


## Método utilizado: Planejamento Ágil
> * São as abordagens interativas nas quais o software é desenvolvido e entregue aos clientes em incrementos e esses incrementos é incluído ou não dependendo do progresso e das prioridades do cliente.
> * Precisa de um cronograma inicial e global de projeto que identifique quando as principais fases do projeto serão concluídas. 
> * A funcionalidades dos incrementos não é planejada com antecedência, mas decidida durante o desenvolvimento.
> 
> SOMMERVILLE, Ian. Engenharia de Software, 9ª Edição. Pearson Education, 2011. *Capítulo 23 - Planejamento de Projeto*.

## Lista de atividades com uma provável data de entrega 

Prazo total: 3,5 meses*

### Atividades Versão 1.0 (foco no cadastro de aluno)
- [ ] Setup do projeto com dropwizard
- [ ] Projetar e implementar classes (entidades) do modelo
- [ ] Implementar camada de persistência (utilizando hibernate e dropwizard)
- [ ] Implementar serviços REST para o cadastro de aluno
- [ ] Realização de teste unitários
- Tempo previsto: 3~7 dias.

### Atividades Versão 2.0 (foco no cadastro de disciplina)
- [ ] Projetar e implementar classes (entidades) do modelo
- [ ] Implementar serviços REST para o cadastro de disciplina
- [ ] Projetar e implementar a conexão/relação entre aluno e disciplina
- [ ] Implementar camada de persistência
- [ ] Realização de teste unitários
- Tempo previsto: 3~7 dias.

### Atividades Versão 3.0 (foco no cadastro de professor)
- [ ] Projetar e implementar classes (entidades) do modelo
- [ ] Implementar serviços REST para o cadastro de disciplina
- [ ] Projetar e implementar a conexão/relação entre as entidades já construídas
- [ ] Implementar camada de persistência 
- [ ] Realização de teste unitários
- Tempo previsto: 3~7 dias.

### Atividades Versão 4.0 (foco na entidade curso)
- [ ] Projetar e implementar classes (entidades) do modelo
- [ ] Implementar serviços REST para o cadastro de curso
- [ ] Projetar e implementar a conexão/relação entre as entidades já construídas
- [ ] Implementar camada de persistência 
- [ ] Realização de teste unitários
- Tempo previsto: 3~7 dias.

### Atividades Versão 5.0 (foco na entidade secretaria)
- [ ] Projetar e implementar classes (entidades) do modelo
- [ ] Implementar serviços REST para o cadastro de secretaria
- [ ] Projetar e implementar a conexão/relação entre as entidades já construídas
- [ ] Implementar camada de persistência 
- [ ] Realização de teste unitários
- Tempo previsto: 3~7 dias.

### Atividades Versão 6.0 (foco na entidade departamento)
- [ ] Projetar e implementar classes (entidades) do modelo
- [ ] Implementar serviços REST para o cadastro de departamento
- [ ] Projetar e implementar a conexão/relação entre as entidades já construídas
- [ ] Implementar camada de persistência 
- [ ] Realização de teste unitários
- Tempo previsto: 3~7 dias.

### Atividades Versão 7.0 (integração das entidades)
- [ ] Projetar e implementar todo o sistema
- [ ] Implementar camada de persistência
- [ ] Realização de teste unitários
- Tempo previsto: 3~7 dias.
