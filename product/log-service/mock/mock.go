// Copyright 2020 Harness Inc. All rights reserved.
// Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
// that can be found in the licenses directory at the root of this repository, also available at
// https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.

package mock

//go:generate mockgen -source=../store/store.go -package=mock -destination=mock_store.go Store
//go:generate mockgen -source=../stream/stream.go -package=mock -destination=mock_stream.go Stream
//go:generate mockgen -source=../client/client.go -package=mock -destination=mock_client.go Client
