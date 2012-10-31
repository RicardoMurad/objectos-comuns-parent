/*
 * Copyright 2012 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.comuns.cnab;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Map;

import br.com.objectos.comuns.io.FixedLine;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
abstract class OcorrenciaParser {

  public Ocorrencia apply(FixedLine line) {
    String text = line.column(108, 110).get(String.class);

    OcorrenciaCodigo codigo = getCodigoMap().get(text);

    return codigo.apply(line);
  }

  abstract Map<String, OcorrenciaCodigo> getCodigoMap();

  abstract static class BuilderWrapper {

    private final Builder<String, OcorrenciaCodigo> map;

    public BuilderWrapper() {
      map = ImmutableMap.<String, OcorrenciaCodigo> builder();
    }

    public OcorrenciaBuilder codigo(int codigo, String descricao) {
      return new OcorrenciaBuilder(codigo, descricao);
    }

    public ImmutableMap<String, OcorrenciaCodigo> build() {
      return map.build();
    }

    abstract OcorrenciaCodigo newInstance(int codigo, String descricao, MotivoParser motivoParser);

    class OcorrenciaBuilder {

      private final int codigo;
      private final String descricao;

      public OcorrenciaBuilder(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
      }

      public MotivoBuilder motivosAt(int pos0, int pos1) {
        return new MotivoBuilder(codigo, descricao, pos0, pos1);
      }

      public BuilderWrapper semMotivo() {
        MotivoParser motivoParser = new MotivoParserVazio();

        OcorrenciaCodigo value = newInstance(codigo, descricao, motivoParser);
        String key = value.get();
        map.put(key, value);

        return BuilderWrapper.this;
      }

    }

    class MotivoBuilder {

      private final int codigo;
      private final String descricao;

      private final int pos0;
      private final int pos1;

      final List<Motivo> motivos = newArrayList();

      public MotivoBuilder(int codigo, String descricao, int pos0, int pos1) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.pos0 = pos0;
        this.pos1 = pos1;
      }

      public MotivoBuilder add(String codigo, String descricao) {
        motivos.add(new Motivo(codigo, descricao));
        return this;
      }

      public BuilderWrapper put() {
        MotivoParser motivoParser = new MotivoParserPadrao(pos0, pos1, motivos);

        OcorrenciaCodigo value = new BradescoOcorrencia(codigo, descricao, motivoParser);
        String key = value.get();
        map.put(key, value);

        return BuilderWrapper.this;
      }

    }

  }

}