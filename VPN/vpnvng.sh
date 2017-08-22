#!/bin/bash

# Set exit on error
set -e

sudo echo "****Welcome to VPN Connection Tool****"
echo "********"
## CONFIG
USERNAME="traitv"
PRIVATE_CODE="200294"

PASSCODE="${1%/}"

if [ -z "$PASSCODE" ]; then
	echo -n "Enter PASSCODE and press [ENTER]: "
	read PASSCODE
	set -f 
fi

{

echo $USERNAME > /tmp/auth.vpn
echo "$PRIVATE_CODE$PASSCODE" >> /tmp/auth.vpn

#sudo openvpn --config /home/longnc/client.ovpn --auth-user-pass /tmp/auth.vpn --log /tmp/logvpn &

sudo openvpn --config /home/cpu11118-local/VPN/client.ovpn --auth-user-pass /tmp/auth.vpn

} || {
 echo "Cannot VPN!"
}

