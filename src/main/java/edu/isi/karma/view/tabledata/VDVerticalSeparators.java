/*******************************************************************************
 * Copyright 2012 University of Southern California
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This code was developed by the Information Integration Group as part 
 * of the Karma project at the Information Sciences Institute of the 
 * University of Southern California.  For more information, publications, 
 * and related projects, please see: http://www.isi.edu/integration
 ******************************************************************************/
/**
 * 
 */
package edu.isi.karma.view.tabledata;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONWriter;

import edu.isi.karma.rep.HNode;
import edu.isi.karma.rep.RepFactory;
import edu.isi.karma.view.VWorkspace;

/**
 * @author szekely
 * 
 */
public class VDVerticalSeparators {

	private final Map<String, VDVerticalSeparator> hNodeIdToVDSEparator = new HashMap<String, VDVerticalSeparator>();

	VDVerticalSeparators() {
		super();
	}

	public VDVerticalSeparator get(String hNodeId) {
		return hNodeIdToVDSEparator.get(hNodeId);
	}

	public void put(String hNodeId, VDVerticalSeparator vdVerticalSeparator) {
		hNodeIdToVDSEparator.put(hNodeId, vdVerticalSeparator);
	}

	/*****************************************************************
	 * 
	 * Debugging Support
	 * 
	 *****************************************************************/

	void prettyPrintJson(JSONWriter jw, VWorkspace vWorkspace)
			throws JSONException {
		RepFactory f = vWorkspace.getRepFactory();
		jw.array();
		for (String key : hNodeIdToVDSEparator.keySet()) {
			String name = "root";
			HNode hn = f.getHNode(key);
			if (hn != null) {
				name = hn.getColumnName();
			}
			jw.object().key(key + "/" + name);
			hNodeIdToVDSEparator.get(key).prettyPrintJson(jw);
			jw.endObject();
		}
		jw.endArray();
	}
}
