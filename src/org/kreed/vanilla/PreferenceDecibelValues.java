/*
 * Copyright (C) 2010 Christopher Eby <kreed@kreed.org>
 *
 * This file is part of Vanilla Music Player.
 *
 * Vanilla Music Player is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Library General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Vanilla Music Player is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.kreed.vanilla;

import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;

/**
 * A selection of decibel values.
 */
public class PreferenceDecibelValues extends ListPreference {
	public PreferenceDecibelValues(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		CharSequence[] entryValues = getEntryValues();
		assert(entryValues != null && entryValues.length > 0);
		
		String[] entryNames = new String[entryValues.length];
		for (int i = 0; i < entryNames.length; ++i) {
			entryNames[i] = String.format("%+.0f db", Float.valueOf(entryValues[i].toString()));
		}

		setEntries(entryNames);
	}
}