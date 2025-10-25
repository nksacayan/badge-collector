#!/usr/bin/env bash
# Usage: ./replace-backend-ip.sh 192.168.1.100
# Replaces the xxx.xxx.xxx.xxx placeholder in frontend/.env with the provided IP (creates a backup).

set -euo pipefail

ENV_FILE="../frontend/.env"

if [ "${#}" -ne 1 ]; then
  echo "Usage: $0 <IPv4-address>"
  exit 2
fi

IP="${1}"

# Basic IPv4 validation
if ! [[ "${IP}" =~ ^([0-9]{1,3}\.){3}[0-9]{1,3}$ ]]; then
  echo "Error: '${IP}' is not a valid IPv4 address format"
  exit 3
fi

# Ensure each octet is 0-255
IFS='.' read -r o1 o2 o3 o4 <<< "${IP}"
for o in $o1 $o2 $o3 $o4; do
  if (( o < 0 || o > 255 )); then
    echo "Error: '${IP}' has an octet outside 0-255"
    exit 4
  fi
done

if [ ! -f "${ENV_FILE}" ]; then
  echo "Error: ${ENV_FILE} not found"
  exit 5
fi

# Replace the placeholder IP segment (handles the exact line shape and any stray dots)
# Matches VITE_BACKEND_API_URL=http://...:8080/api and replaces the host with the supplied IP
sed -E -e "s|^(VITE_BACKEND_API_URL=https?://)[^:/]+(:8080/api.*)$|\1${IP}\2|" -i "${ENV_FILE}"

echo "Updated ${ENV_FILE}"