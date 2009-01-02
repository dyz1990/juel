/*
 * Copyright 2006, 2007 Odysseus Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package de.odysseus.el.tree.impl.ast;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.MethodInfo;

import de.odysseus.el.misc.LocalMessages;
import de.odysseus.el.tree.Bindings;

/**
 * @author Christoph Beck
 */
public abstract class AstRightValue extends AstNode {
	/**
	 * Answer <code>false</code>
	 */
	public final boolean isLiteralText() {
		return false;
	}

	/**
	 * according to the spec, the result is undefined for rvalues, so answer <code>null</code>
	 */
	public final Class<?> getType(Bindings bindings, ELContext context) {
		return null;
	}

	/**
	 * non-lvalues are always readonly, so answer <code>true</code>
	 */
	public final boolean isReadOnly(Bindings bindings, ELContext context) {
		return true;
	}

	/**
	 * non-lvalues are always readonly, so throw an exception
	 */
	public final void setValue(Bindings bindings, ELContext context, Object value) {
		throw new ELException(LocalMessages.get("error.value.set.rvalue"));
	}

	public final MethodInfo getMethodInfo(Bindings bindings, ELContext context, Class<?> returnType, Class<?>[] paramTypes) {
		return null;
	}

	public final Object invoke(Bindings bindings, ELContext context, Class<?> returnType, Class<?>[] paramTypes, Object[] paramValues) {
		throw new ELException(LocalMessages.get("error.method.invalid", getStructuralId(bindings)));
	}

	public final boolean isLeftValue() {
		return false;
	}
}