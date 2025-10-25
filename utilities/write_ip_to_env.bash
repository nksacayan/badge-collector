#!/usr/bin/env bash
set -euo pipefail

# Usage: ./create-frontend-env.sh 192.168.1.50
# Expects only IPv4 octets as the single argument.

TARGET_DIR="../frontend"
ENV_FILE="${TARGET_DIR}/.env"
TMP_FILE="${ENV_FILE}.tmp"

if [ "${#}" -ne 1 ]; then
  echo "Usage: $0 <ipv4-address e.g. 192.168.1.50>" >&2
  exit 2
fi

IP="$1"

# Validate IPv4: four octets 0-255
if ! [[ "${IP}" =~ ^([0-9]{1,3})\.([0-9]{1,3})\.([0-9]{1,3})\.([0-9]{1,3})$ ]]; then
  echo "Error: input is not in IPv4 dotted format" >&2
  exit 3
fi

# Check each octet range
IFS='.' read -r o1 o2 o3 o4 <<< "${IP}"
for oct in "${o1}" "${o2}" "${o3}" "${o4}"; do
  if [ "${oct#0}" != "${oct}" ] && [ "${oct}" != "0" ]; then
    : # allow leading zeros but keep numeric check
  fi
  if [ "${oct}" -lt 0 ] 2>/dev/null || [ "${oct}" -gt 255 ] 2>/dev/null; then
    echo "Error: each octet must be between 0 and 255" >&2
    exit 4
  fi
done

VITE_BACKEND_API_URL="http://${IP}:8080/api"

mkdir -p "${TARGET_DIR}"

{
  printf 'VITE_BACKEND_API_URL="%s"\n' "${VITE_BACKEND_API_URL}"
} > "${TMP_FILE}"

mv "${TMP_FILE}" "${ENV_FILE}"
echo "Wrote ${ENV_FILE} with VITE_BACKEND_API_URL=${VITE_BACKEND_API_URL}"