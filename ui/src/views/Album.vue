<template>
  <div>
    <div id="form" v-if="seen() === true">
      <form @submit.prevent="cadastrar">
        <h2>Albums</h2>
        <div class="form-group">
          <label for="nomeAlbum">Nome do Album</label
          ><input
            type="text"
            id="nomeAlbum"
            class="form-control"
            required
            autofocus
            v-model="nomeAlbum"
        />
        </div>
        <div class="form-group">
          <label>Ano do Album</label
          ><input
            type="number"
            id="anoAlbum"
            class="form-control"
            required
            v-model="anoAlbum"
        />
        </div>
        <div class="form-group">
          <label>Nomes dos Artistas</label>
          <input
              type="text"
              id="listaFaixas"
              class="form-control"
              required
              v-model="listaFaixas"
          />
        </div>
        <div class="form-group">
          <label>Lista de Faixas</label>
          <input
              type="text"
              id="nomesArtistas"
              class="form-control"
              required
              v-model="nomesArtistas"
          />
        </div>
        <div class="form-group">
          <label>Durações das Faixas</label>
          <input
              type="text"
              id="duracoes"
              class="form-control"
              required
              v-model="duracoes"
          />
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Salvar
        </button>
      </form>
      <br/>
    </div>
    <table class="table table-striped">
      <thead>
      <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Ano</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="album in albums" :key="album.albumId">
        <td>{{ album.albumId }}</td>
        <td>{{ album.nome }}</td>
        <td>{{ album.ano }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import axios from "axios";
import {mapState} from "vuex";

export default {
  name: "albums",
  data() {
    return {
      nomeAlbum: "",
      anoAlbum: null,
      nomesArtistas: "",
      listaFaixas: "",
      duracoes: "",
      albums: [],
    };
  },
  computed: {...mapState(["usuario", "autorizacao"])},
  methods: {
    seen() {
      console.log(this.autorizacao);
      console.log(this.autorizacao.includes('[ROLE_ADMIN]'));
      return this.autorizacao.includes('[ROLE_ADMIN]');
    },
    cadastrar() {
      axios
          .post("/album", {
            nomeAlbum: this.nomeAlbum,
            anoAlbum: this.anoAlbum,
            nomesArtistas: this.getNomesArtistas(),
            listaFaixas: this.getListaFaixas(),
          })
          .then((res) => {
            console.log(res);
            this.nomeAlbum = "";
            this.anoAlbum = null;
            this.nomesArtistas = "";
            this.listaFaixas = "";
            this.duracoes = "";
            this.atualizar();
          })
          .catch((error) => console.log(error));
    },
    atualizar() {
      axios
          .get("/albums", {
            headers: {Accept: "application/json"},
          })
          .then((res) => {
            console.log(res);
            this.albums = res.data;
          })
          .catch((error) => console.log(error));
    },
    getNomesArtistas() {
      return this.nomesArtistas.split(",");
    },
    getListaFaixas() {
      const nomes = this.listaFaixas.split(",");
      const duracao = this.duracoes.split(",");
      const listaFaixas = [];
      nomes.forEach((nome, index) => {
        listaFaixas.push({
          nome: nome,
          duracao: duracao[index] * 1,
        });
      });
      return listaFaixas;
    },
  },
  created() {
    this.atualizar();
  },
};
</script>
