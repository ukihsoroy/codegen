using static Luxshare.Api.Entities.Enums.CommonEnum;

namespace Luxshare.Api.RequestPayload.Report.${entityName}
{

    public class ${entityName}RequestPayload : RequestPayload
    {

    <#list columns as column>
        public ${column.propertyType} ${column.columnName} { get; set; }

    </#list>
        public IsDeleted IsDeleted { get; set; }

        public Status Status { get; set; }
    }
}
