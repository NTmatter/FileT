package filet

class FiletUser {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true, email: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<FiletRole> getAuthorities() {
		FiletUserFiletRole.findAllByFiletUser(this).collect { it.filetRole } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
