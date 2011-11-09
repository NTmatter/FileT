package filet

import org.apache.commons.lang.builder.HashCodeBuilder

class FiletUserFiletRole implements Serializable {

	FiletUser filetUser
	FiletRole filetRole

	boolean equals(other) {
		if (!(other instanceof FiletUserFiletRole)) {
			return false
		}

		other.filetUser?.id == filetUser?.id &&
			other.filetRole?.id == filetRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (filetUser) builder.append(filetUser.id)
		if (filetRole) builder.append(filetRole.id)
		builder.toHashCode()
	}

	static FiletUserFiletRole get(long filetUserId, long filetRoleId) {
		find 'from FiletUserFiletRole where filetUser.id=:filetUserId and filetRole.id=:filetRoleId',
			[filetUserId: filetUserId, filetRoleId: filetRoleId]
	}

	static FiletUserFiletRole create(FiletUser filetUser, FiletRole filetRole, boolean flush = false) {
		new FiletUserFiletRole(filetUser: filetUser, filetRole: filetRole).save(flush: flush, insert: true)
	}

	static boolean remove(FiletUser filetUser, FiletRole filetRole, boolean flush = false) {
		FiletUserFiletRole instance = FiletUserFiletRole.findByFiletUserAndFiletRole(filetUser, filetRole)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(FiletUser filetUser) {
		executeUpdate 'DELETE FROM FiletUserFiletRole WHERE filetUser=:filetUser', [filetUser: filetUser]
	}

	static void removeAll(FiletRole filetRole) {
		executeUpdate 'DELETE FROM FiletUserFiletRole WHERE filetRole=:filetRole', [filetRole: filetRole]
	}

	static mapping = {
		id composite: ['filetRole', 'filetUser']
		version false
	}
}
