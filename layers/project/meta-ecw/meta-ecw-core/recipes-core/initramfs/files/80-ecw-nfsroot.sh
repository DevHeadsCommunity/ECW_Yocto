#!/bin/sh

if [ -n "$bootparam_debug" ] ; then
	set -x
fi

if [ -n "$bootparam_breakout" ] ; then
	/bin/sh
fi



ecw_enabled() {
	if [ ${bootparam_root} != "/dev/nfs" ] || [ -z ${bootparam_server_ip} ]; then
		return 1
	fi
	return 0
}

ecw_run() {
	local nfs_opts
	local location
	local flags
	local server_ip

	info "Starting NFS Mount"

	nfs_opts="${bootparam_nfs_opts}"
	if [ "${bootparam_server_ip#*,}" != "${bootparam_server_ip}" ]; then
		nfs_opts="-o ${bootparam_server_ip#*,}"
	fi

	server="${bootparam_server_ip}"

	flags="-o nolock"
	if [ -n "$bootparam_ro" ] && ! echo "$bootparam_rootflags" | grep -w -q "ro"; then
		if [  -n "$bootparam_rootflags" ]; then
			bootparam_rootflags="$bootparam_rootflags,"
		fi
		bootparam_rootflags="${bootparam_rootflags}ro"
	fi
	if [ -n "$bootparam_rootflags" ]; then
		flags="$flags -o $bootparam_rootflags"
	fi

	ip_info="$(ip a)"
	info $ip_info

	# If the IP Address has not been set, try again
	if [ ! $(echo $ip_info | grep inet ) ]; then
	  info "Trying to get an IP address"
      udhcpc eth0
	fi

	ip_info="$(ip a)"
	info $ip_info

	mac_address=$(ip a | grep link/ether | awk '{print $2}' | tr '[:lower:]' '[:upper:]')
	info mac_address

	echo "Mounting NFS..."
	
	# Expect server_ip=xx.xx.xx.xx
	info "mount -t nfs4 ${flags} ${nfs_opts} ${server}:/${mac_address} ${ROOTFS_DIR}"
	
	mount -t nfs4 ${flags} ${nfs_opts} ${server}:/${mac_address} ${ROOTFS_DIR}

}

