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

package org.kreed.vanilladev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.DialogPreference;
import android.preference.PreferenceActivity;
import android.view.KeyEvent;
import org.kreed.vanilladev.R;

/**
 * The preferences activity in which one can change application preferences.
 */
public class PreferencesActivity 
	extends PreferenceActivity
	implements SharedPreferences.OnSharedPreferenceChangeListener 
{
	SharedPreferences mSettings;
	
	/**
	 * Initialize the activity, loading the preference specifications.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ContextApplication.addActivity(this);
		addPreferencesFromResource(R.xml.preferences);

		mSettings = ContextApplication.getContext().getSettings();
		mSettings.registerOnSharedPreferenceChangeListener(this);
		
		updateReplayGainPreferenceState();
	}

	@Override 
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) 
	{
		updateReplayGainPreferenceState();
	}
	
	/**
	 * Returns all of the preference keys that should be enabled or disabled according to the 
	 * state of the replaygain_enabled setting, excluding "replaygain_enabled".
	 */
	protected String[] replaygainPreferenceKeys()
	{
		return new String[] {
				"replaygain_max_boost",
				"replaygain_no_data_attenuation"
		};
	}
	
	/**
	 * Gets an instance of a DialogPreference for the given preference key.
	 * @param key
	 */
	protected DialogPreference getDialogPreference(String key)
	{
		return ((DialogPreference)this.getPreferenceManager().findPreference(key));
	}
	
	/**
	 * Updates the 'enabled' state of all preferences relating to replaygain, according to whether
	 * replaygain processing is enabled or not.
	 */
	protected void updateReplayGainPreferenceState()
	{
		boolean replaygainEnabled = ((CheckBoxPreference)this.getPreferenceManager().findPreference("enable_replaygain")).isChecked();
		
		for (String key : replaygainPreferenceKeys()) {
			getDialogPreference(key).setEnabled(replaygainEnabled);
		}
		
		getDialogPreference("volume").setEnabled(!replaygainEnabled);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		ContextApplication.removeActivity(this);
	}

	/**
	 * Implement the long-press-back-quits-application behavior.
	 */
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event)
	{
		return PlaybackActivity.handleKeyLongPress(keyCode);
	}
}
