/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.util;

import org.spout.vanilla.data.effect.SoundEffect;
import org.spout.vanilla.data.effect.store.SoundEffects;
import org.spout.vanilla.data.effect.type.NoteBlockEffect;

public enum Instrument {
	PIANO(0, "harp", SoundEffects.NOTE_HARP),
	BASSDRUM(1, "bd", SoundEffects.NOTE_BD),
	SNAREDRUM(2, "snare", SoundEffects.NOTE_SNARE),
	CLICK(3, "hat", SoundEffects.NOTE_HAT),
	BASSGUITAR(4, "bassattack", SoundEffects.NOTE_BASSATTACK);
	private byte id;
	private String name;
	private NoteBlockEffect effect;

	private Instrument(int id, String name, SoundEffect effect) {
		this.id = (byte) id;
		this.name = name;
		this.effect = new NoteBlockEffect(effect);
	}

	public String getName() {
		return this.name;
	}

	public byte getId() {
		return this.id;
	}

	public NoteBlockEffect getEffect() {
		return this.effect;
	}
}
