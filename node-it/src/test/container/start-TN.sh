#!/bin/bash
echo Options: $TN_OPTS
exec java $TN_OPTS -cp "/opt/TN/lib/*" com.wavesplatform.Application /opt/TN/template.conf
