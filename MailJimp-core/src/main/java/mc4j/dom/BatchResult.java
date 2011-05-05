/*
 * Copyright 2011 Eike Hirsch
 * 
 * This file is part of MailJimp.
 * 
 * MailJimp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MailJimp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with MailJimp.  If not, see <http://www.gnu.org/licenses/>.
 */

package mc4j.dom;

/**
 * Represents the response to an batch operation like {@link mc4j.service.IMailChimpService#listBatchSubscribe(String, Object[], boolean, boolean, boolean)}.
 *
 * Author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 27.04.11
 * Time: 10:36
 */
public class BatchResult {

	private int addCount;
	private int successCount;
	private int updateCount;
	private int errorCount;
	private BatchError[] errors;


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder
			.append("BatchResult{")
			.append("addCount=").append(addCount)
			.append(", successCount=").append(successCount)
			.append(", updateCount=").append(updateCount)
			.append(", errorCount=").append(errorCount)
			.append(", errors=[");

		if( null != errors ) {
			for (int i = 0; i < errors.length; i++) {
				BatchError error = errors[i];
				if( 0<i) {
					builder.append(", ");
				}
				builder.append(error.toString());
			}
		}

		builder.append("]}");

		return builder.toString();
	}

	public int getAddCount() {
		return addCount;
	}

	public void setAddCount(Integer addCount) {
		if( null == addCount ) {
			this.addCount = 0;
		} else {
			this.addCount = addCount;
		}
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		if( null == successCount ) {
			this.successCount = 0;
		} else {
			this.successCount = successCount;
		}
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(Integer updateCount) {
		if( null == updateCount ) {
			this.updateCount = 0;
		} else {
			this.updateCount = updateCount;
		}
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		if( null == errorCount ) {
			this.errorCount = 0;
		} else {
			this.errorCount = errorCount;
		}
	}

	public BatchError[] getErrors() {
		return errors;
	}

	public void setErrors(BatchError[] errors) {
		this.errors = errors;
	}
}
