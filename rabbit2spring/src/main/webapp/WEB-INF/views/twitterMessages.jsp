<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
                <p>This example searches Twitter and shows the results below.</p>
                <ul class="twitterMessages">
                    <c:choose>
                        <c:when test="${not empty twitterMessages}">
                            <c:forEach items="${twitterMessages}" var="twitterMessage">
                                <li><c:out value="${twitterMessage}"/></li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>No Twitter messages found.</c:otherwise>
                    </c:choose>
                </ul>
