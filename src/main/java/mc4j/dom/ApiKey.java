/**
 * Copyright 2010 Michael Laccetti
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package mc4j.dom;

import java.util.Date;

public class ApiKey {
	private String apiKey;
	private Date createdAt;
	private Date expiredAt;
	
	public ApiKey() {
		// empty
	}

	public ApiKey(String apiKey, Date createdAt, Date expiredAt) {
		this.apiKey = apiKey;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
	}

	@Override
	public String toString() {
		return "ApiKey [apiKey=" + apiKey + ", createdAt=" + createdAt + ", expiredAt=" + expiredAt + "]";
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}
}