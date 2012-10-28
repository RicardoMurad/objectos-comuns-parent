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
package br.com.objectos.comuns.sitebricks.relational;

import java.util.List;

/**
 * @author marcio.endo@objectos.com.br (Marcio Endo)
 */
public class PagerList<T> {

  private final boolean empty;

  private final List<T> rows;

  private final Pager pager;

  public PagerList(List<T> rows, Pager pager) {
    this.empty = rows.isEmpty();
    this.rows = rows;
    this.pager = pager;
  }

  public boolean isEmpty() {
    return empty;
  }

  public List<T> getRows() {
    return rows;
  }

  public Pager getPager() {
    return pager;
  }

}